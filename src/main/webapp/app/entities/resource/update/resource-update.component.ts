import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ISurvivor } from 'app/entities/survivor/survivor.model';
import { SurvivorService } from 'app/entities/survivor/service/survivor.service';
import { ResourceType } from 'app/entities/enumerations/resource-type.model';
import { ResourceService } from '../service/resource.service';
import { IResource } from '../resource.model';
import { ResourceFormGroup, ResourceFormService } from './resource-form.service';

@Component({
  selector: 'jhi-resource-update',
  templateUrl: './resource-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ResourceUpdateComponent implements OnInit {
  isSaving = false;
  resource: IResource | null = null;
  resourceTypeValues = Object.keys(ResourceType);

  survivorsSharedCollection: ISurvivor[] = [];

  protected resourceService = inject(ResourceService);
  protected resourceFormService = inject(ResourceFormService);
  protected survivorService = inject(SurvivorService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ResourceFormGroup = this.resourceFormService.createResourceFormGroup();

  compareSurvivor = (o1: ISurvivor | null, o2: ISurvivor | null): boolean => this.survivorService.compareSurvivor(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resource }) => {
      this.resource = resource;
      if (resource) {
        this.updateForm(resource);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resource = this.resourceFormService.getResource(this.editForm);
    if (resource.id !== null) {
      this.subscribeToSaveResponse(this.resourceService.update(resource));
    } else {
      this.subscribeToSaveResponse(this.resourceService.create(resource));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResource>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(resource: IResource): void {
    this.resource = resource;
    this.resourceFormService.resetForm(this.editForm, resource);

    this.survivorsSharedCollection = this.survivorService.addSurvivorToCollectionIfMissing<ISurvivor>(
      this.survivorsSharedCollection,
      resource.survivor,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.survivorService
      .query()
      .pipe(map((res: HttpResponse<ISurvivor[]>) => res.body ?? []))
      .pipe(
        map((survivors: ISurvivor[]) =>
          this.survivorService.addSurvivorToCollectionIfMissing<ISurvivor>(survivors, this.resource?.survivor),
        ),
      )
      .subscribe((survivors: ISurvivor[]) => (this.survivorsSharedCollection = survivors));
  }
}
