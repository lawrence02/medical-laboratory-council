import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ISurvivor, NewSurvivor } from '../survivor.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISurvivor for edit and NewSurvivorFormGroupInput for create.
 */
type SurvivorFormGroupInput = ISurvivor | PartialWithRequiredKeyOf<NewSurvivor>;

type SurvivorFormDefaults = Pick<NewSurvivor, 'id'>;

type SurvivorFormGroupContent = {
  id: FormControl<ISurvivor['id'] | NewSurvivor['id']>;
  registrationNumber: FormControl<ISurvivor['registrationNumber']>;
  surname: FormControl<ISurvivor['surname']>;
  forenames: FormControl<ISurvivor['forenames']>;
  previousSurname: FormControl<ISurvivor['previousSurname']>;
  dob: FormControl<ISurvivor['dob']>;
  gender: FormControl<ISurvivor['gender']>;
};

export type SurvivorFormGroup = FormGroup<SurvivorFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class SurvivorFormService {
  createSurvivorFormGroup(survivor: SurvivorFormGroupInput = { id: null }): SurvivorFormGroup {
    const survivorRawValue = {
      ...this.getFormDefaults(),
      ...survivor,
    };
    return new FormGroup<SurvivorFormGroupContent>({
      id: new FormControl(
        { value: survivorRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      registrationNumber: new FormControl(survivorRawValue.registrationNumber, {
        validators: [Validators.required],
      }),
      surname: new FormControl(survivorRawValue.surname, {
        validators: [Validators.required],
      }),
      forenames: new FormControl(survivorRawValue.forenames, {
        validators: [Validators.required],
      }),
      previousSurname: new FormControl(survivorRawValue.previousSurname),
      dob: new FormControl(survivorRawValue.dob, {
        validators: [Validators.required],
      }),
      gender: new FormControl(survivorRawValue.gender, {
        validators: [Validators.required],
      }),
    });
  }

  getSurvivor(form: SurvivorFormGroup): ISurvivor | NewSurvivor {
    return form.getRawValue() as ISurvivor | NewSurvivor;
  }

  resetForm(form: SurvivorFormGroup, survivor: SurvivorFormGroupInput): void {
    const survivorRawValue = { ...this.getFormDefaults(), ...survivor };
    form.reset(
      {
        ...survivorRawValue,
        id: { value: survivorRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): SurvivorFormDefaults {
    return {
      id: null,
    };
  }
}
