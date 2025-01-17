import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { Gender } from 'app/entities/enumerations/gender.model';
import { MaritalStatus } from 'app/entities/enumerations/marital-status.model';
import { ISurvivor } from '../survivor.model';
import { SurvivorService } from '../service/survivor.service';
import { SurvivorFormGroup, SurvivorFormService } from './survivor-form.service';

@Component({
  selector: 'jhi-survivor-update',
  templateUrl: './survivor-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class SurvivorUpdateComponent implements OnInit {
  isSaving = false;
  survivor: ISurvivor | null = null;
  genderValues = Object.keys(Gender);
  maritalStatusValues = Object.keys(MaritalStatus);

  protected survivorService = inject(SurvivorService);
  protected survivorFormService = inject(SurvivorFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: SurvivorFormGroup = this.survivorFormService.createSurvivorFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ survivor }) => {
      this.survivor = survivor;
      if (survivor) {
        this.updateForm(survivor);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const survivor = this.survivorFormService.getSurvivor(this.editForm);
    if (survivor.id !== null) {
      this.subscribeToSaveResponse(this.survivorService.update(survivor));
    } else {
      this.subscribeToSaveResponse(this.survivorService.create(survivor));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISurvivor>>): void {
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

  protected updateForm(survivor: ISurvivor): void {
    this.survivor = survivor;
    this.survivorFormService.resetForm(this.editForm, survivor);
  }
}
