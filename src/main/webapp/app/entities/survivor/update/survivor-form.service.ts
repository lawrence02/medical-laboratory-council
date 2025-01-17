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
  placeOfBirthTown: FormControl<ISurvivor['placeOfBirthTown']>;
  placeOfBirthCountry: FormControl<ISurvivor['placeOfBirthCountry']>;
  nationality: FormControl<ISurvivor['nationality']>;
  nationalId: FormControl<ISurvivor['nationalId']>;
  maritalStatus: FormControl<ISurvivor['maritalStatus']>;
  residentialAddress1: FormControl<ISurvivor['residentialAddress1']>;
  residentialAddress2: FormControl<ISurvivor['residentialAddress2']>;
  residentialAddress3: FormControl<ISurvivor['residentialAddress3']>;
  homePhone: FormControl<ISurvivor['homePhone']>;
  workPhone: FormControl<ISurvivor['workPhone']>;
  cellPhone: FormControl<ISurvivor['cellPhone']>;
  emailAddress: FormControl<ISurvivor['emailAddress']>;
  nameOfPlaceOfEmployment: FormControl<ISurvivor['nameOfPlaceOfEmployment']>;
  employerAddress: FormControl<ISurvivor['employerAddress']>;
  employerEmail: FormControl<ISurvivor['employerEmail']>;
  dateOfEmployment: FormControl<ISurvivor['dateOfEmployment']>;
  reasonForNonEmployment: FormControl<ISurvivor['reasonForNonEmployment']>;
  dateOfApplication: FormControl<ISurvivor['dateOfApplication']>;
  applicationFee: FormControl<ISurvivor['applicationFee']>;
  status: FormControl<ISurvivor['status']>;
  reasonNotApproved: FormControl<ISurvivor['reasonNotApproved']>;
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
      placeOfBirthTown: new FormControl(survivorRawValue.placeOfBirthTown),
      placeOfBirthCountry: new FormControl(survivorRawValue.placeOfBirthCountry),
      nationality: new FormControl(survivorRawValue.nationality),
      nationalId: new FormControl(survivorRawValue.nationalId),
      maritalStatus: new FormControl(survivorRawValue.maritalStatus),
      residentialAddress1: new FormControl(survivorRawValue.residentialAddress1),
      residentialAddress2: new FormControl(survivorRawValue.residentialAddress2),
      residentialAddress3: new FormControl(survivorRawValue.residentialAddress3),
      homePhone: new FormControl(survivorRawValue.homePhone),
      workPhone: new FormControl(survivorRawValue.workPhone),
      cellPhone: new FormControl(survivorRawValue.cellPhone),
      emailAddress: new FormControl(survivorRawValue.emailAddress),
      nameOfPlaceOfEmployment: new FormControl(survivorRawValue.nameOfPlaceOfEmployment),
      employerAddress: new FormControl(survivorRawValue.employerAddress),
      employerEmail: new FormControl(survivorRawValue.employerEmail),
      dateOfEmployment: new FormControl(survivorRawValue.dateOfEmployment),
      reasonForNonEmployment: new FormControl(survivorRawValue.reasonForNonEmployment),
      dateOfApplication: new FormControl(survivorRawValue.dateOfApplication),
      applicationFee: new FormControl(survivorRawValue.applicationFee),
      status: new FormControl(survivorRawValue.status),
      reasonNotApproved: new FormControl(survivorRawValue.reasonNotApproved),
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
