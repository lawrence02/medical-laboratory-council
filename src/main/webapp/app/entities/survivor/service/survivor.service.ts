import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISurvivor, NewSurvivor } from '../survivor.model';

export type PartialUpdateSurvivor = Partial<ISurvivor> & Pick<ISurvivor, 'id'>;

type RestOf<T extends ISurvivor | NewSurvivor> = Omit<T, 'dob'> & {
  dob?: string | null;
};

export type RestSurvivor = RestOf<ISurvivor>;

export type NewRestSurvivor = RestOf<NewSurvivor>;

export type PartialUpdateRestSurvivor = RestOf<PartialUpdateSurvivor>;

export type EntityResponseType = HttpResponse<ISurvivor>;
export type EntityArrayResponseType = HttpResponse<ISurvivor[]>;

@Injectable({ providedIn: 'root' })
export class SurvivorService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/survivors');

  create(survivor: NewSurvivor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(survivor);
    return this.http
      .post<RestSurvivor>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(survivor: ISurvivor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(survivor);
    return this.http
      .put<RestSurvivor>(`${this.resourceUrl}/${this.getSurvivorIdentifier(survivor)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(survivor: PartialUpdateSurvivor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(survivor);
    return this.http
      .patch<RestSurvivor>(`${this.resourceUrl}/${this.getSurvivorIdentifier(survivor)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestSurvivor>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestSurvivor[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getSurvivorIdentifier(survivor: Pick<ISurvivor, 'id'>): number {
    return survivor.id;
  }

  compareSurvivor(o1: Pick<ISurvivor, 'id'> | null, o2: Pick<ISurvivor, 'id'> | null): boolean {
    return o1 && o2 ? this.getSurvivorIdentifier(o1) === this.getSurvivorIdentifier(o2) : o1 === o2;
  }

  addSurvivorToCollectionIfMissing<Type extends Pick<ISurvivor, 'id'>>(
    survivorCollection: Type[],
    ...survivorsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const survivors: Type[] = survivorsToCheck.filter(isPresent);
    if (survivors.length > 0) {
      const survivorCollectionIdentifiers = survivorCollection.map(survivorItem => this.getSurvivorIdentifier(survivorItem));
      const survivorsToAdd = survivors.filter(survivorItem => {
        const survivorIdentifier = this.getSurvivorIdentifier(survivorItem);
        if (survivorCollectionIdentifiers.includes(survivorIdentifier)) {
          return false;
        }
        survivorCollectionIdentifiers.push(survivorIdentifier);
        return true;
      });
      return [...survivorsToAdd, ...survivorCollection];
    }
    return survivorCollection;
  }

  protected convertDateFromClient<T extends ISurvivor | NewSurvivor | PartialUpdateSurvivor>(survivor: T): RestOf<T> {
    return {
      ...survivor,
      dob: survivor.dob?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restSurvivor: RestSurvivor): ISurvivor {
    return {
      ...restSurvivor,
      dob: restSurvivor.dob ? dayjs(restSurvivor.dob) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestSurvivor>): HttpResponse<ISurvivor> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestSurvivor[]>): HttpResponse<ISurvivor[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
