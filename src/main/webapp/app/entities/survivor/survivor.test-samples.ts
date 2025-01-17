import dayjs from 'dayjs/esm';

import { ISurvivor, NewSurvivor } from './survivor.model';

export const sampleWithRequiredData: ISurvivor = {
  id: 8744,
  registrationNumber: 'exacerbate boo geez',
  surname: 'ordinary',
  forenames: 'whoa mmm satirise',
  dob: dayjs('2025-01-16'),
  gender: 'Male',
};

export const sampleWithPartialData: ISurvivor = {
  id: 17506,
  practitionerType: 'Technician',
  registrationNumber: 'freely',
  title: 'Ms',
  surname: 'since really energetically',
  forenames: 'acquaintance helplessly',
  previousSurname: 'pish yowza forgo',
  dob: dayjs('2025-01-16'),
  gender: 'Female',
  placeOfBirthTown: 'snoopy breed now',
  placeOfBirthCountry: 'vibraphone circumnavigate outflank',
  nationality: 'markup netsuke courteous',
  nationalId: 'able yet inside',
  maritalStatus: 'Married',
  residentialAddress1: 'ick',
  residentialAddress3: 'bidet yellowish after',
  homePhone: 'really',
  emailAddress: 'avow distinction',
  nameOfPlaceOfEmployment: 'excluding',
  employerEmail: 'notwithstanding',
  dateOfEmployment: 'finally accept',
  employmentStatus: 'Temporary',
  typeOfInstitution: 'Other',
  dateOfApplication: 'upon down',
  status: 'questionably quizzically',
};

export const sampleWithFullData: ISurvivor = {
  id: 5085,
  practitionerType: 'StateCertifiedTechnician',
  registrationNumber: 'outnumber cheerfully blah',
  title: 'Dr',
  surname: 'gadzooks inasmuch delightfully',
  forenames: 'male whose or',
  previousSurname: 'bar blend',
  dob: dayjs('2025-01-16'),
  gender: 'Unknown',
  placeOfBirthTown: 'lustrous wrongly',
  placeOfBirthCountry: 'yearningly yogurt',
  nationality: 'hm',
  nationalId: 'heavenly',
  maritalStatus: 'Married',
  residentialAddress1: 'that triangular unto',
  residentialAddress2: 'volleyball',
  residentialAddress3: 'keenly',
  homePhone: 'pleased haze',
  workPhone: 'because citizen',
  cellPhone: 'whenever on advertisement',
  emailAddress: 'cosset across',
  nameOfPlaceOfEmployment: 'probe ah ick',
  employerAddress: 'who woeful',
  employerEmail: 'uh-huh',
  dateOfEmployment: 'trash',
  areaOfEmployment: 'LocalAuthority',
  employmentStatus: 'FullTime',
  typeOfInstitution: 'Mines',
  provinceEmployed: 'Manicaland',
  reasonForNonEmployment: 'vet opposite ugh',
  dateOfApplication: 'until off likewise',
  applicationFee: 'of',
  status: 'woot season',
  reasonNotApproved: 'implode drat',
};

export const sampleWithNewData: NewSurvivor = {
  registrationNumber: 'fooey consequently',
  surname: 'geez',
  forenames: 'progress wherever',
  dob: dayjs('2025-01-15'),
  gender: 'Female',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
