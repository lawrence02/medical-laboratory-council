import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../survivor.test-samples';

import { SurvivorFormService } from './survivor-form.service';

describe('Survivor Form Service', () => {
  let service: SurvivorFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurvivorFormService);
  });

  describe('Service methods', () => {
    describe('createSurvivorFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSurvivorFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            registrationNumber: expect.any(Object),
            surname: expect.any(Object),
            forenames: expect.any(Object),
            previousSurname: expect.any(Object),
            dob: expect.any(Object),
            gender: expect.any(Object),
            placeOfBirthTown: expect.any(Object),
            placeOfBirthCountry: expect.any(Object),
            nationality: expect.any(Object),
            nationalId: expect.any(Object),
            maritalStatus: expect.any(Object),
            residentialAddress1: expect.any(Object),
            residentialAddress2: expect.any(Object),
            residentialAddress3: expect.any(Object),
            homePhone: expect.any(Object),
            workPhone: expect.any(Object),
            cellPhone: expect.any(Object),
            emailAddress: expect.any(Object),
            nameOfPlaceOfEmployment: expect.any(Object),
            employerAddress: expect.any(Object),
            employerEmail: expect.any(Object),
            dateOfEmployment: expect.any(Object),
            reasonForNonEmployment: expect.any(Object),
            dateOfApplication: expect.any(Object),
            applicationFee: expect.any(Object),
            status: expect.any(Object),
            reasonNotApproved: expect.any(Object),
          }),
        );
      });

      it('passing ISurvivor should create a new form with FormGroup', () => {
        const formGroup = service.createSurvivorFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            registrationNumber: expect.any(Object),
            surname: expect.any(Object),
            forenames: expect.any(Object),
            previousSurname: expect.any(Object),
            dob: expect.any(Object),
            gender: expect.any(Object),
            placeOfBirthTown: expect.any(Object),
            placeOfBirthCountry: expect.any(Object),
            nationality: expect.any(Object),
            nationalId: expect.any(Object),
            maritalStatus: expect.any(Object),
            residentialAddress1: expect.any(Object),
            residentialAddress2: expect.any(Object),
            residentialAddress3: expect.any(Object),
            homePhone: expect.any(Object),
            workPhone: expect.any(Object),
            cellPhone: expect.any(Object),
            emailAddress: expect.any(Object),
            nameOfPlaceOfEmployment: expect.any(Object),
            employerAddress: expect.any(Object),
            employerEmail: expect.any(Object),
            dateOfEmployment: expect.any(Object),
            reasonForNonEmployment: expect.any(Object),
            dateOfApplication: expect.any(Object),
            applicationFee: expect.any(Object),
            status: expect.any(Object),
            reasonNotApproved: expect.any(Object),
          }),
        );
      });
    });

    describe('getSurvivor', () => {
      it('should return NewSurvivor for default Survivor initial value', () => {
        const formGroup = service.createSurvivorFormGroup(sampleWithNewData);

        const survivor = service.getSurvivor(formGroup) as any;

        expect(survivor).toMatchObject(sampleWithNewData);
      });

      it('should return NewSurvivor for empty Survivor initial value', () => {
        const formGroup = service.createSurvivorFormGroup();

        const survivor = service.getSurvivor(formGroup) as any;

        expect(survivor).toMatchObject({});
      });

      it('should return ISurvivor', () => {
        const formGroup = service.createSurvivorFormGroup(sampleWithRequiredData);

        const survivor = service.getSurvivor(formGroup) as any;

        expect(survivor).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISurvivor should not enable id FormControl', () => {
        const formGroup = service.createSurvivorFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSurvivor should disable id FormControl', () => {
        const formGroup = service.createSurvivorFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
