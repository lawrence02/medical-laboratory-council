import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISurvivor } from '../survivor.model';
import { SurvivorService } from '../service/survivor.service';

const survivorResolve = (route: ActivatedRouteSnapshot): Observable<null | ISurvivor> => {
  const id = route.params.id;
  if (id) {
    return inject(SurvivorService)
      .find(id)
      .pipe(
        mergeMap((survivor: HttpResponse<ISurvivor>) => {
          if (survivor.body) {
            return of(survivor.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default survivorResolve;
