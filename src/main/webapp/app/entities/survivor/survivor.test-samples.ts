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
  id: 192,
  practitionerType: 'Bank',
  registrationNumber: 'premise',
  title: 'Dr',
  surname: 'definitive',
  forenames: 'baseboard as forenenst',
  previousSurname: 'till',
  dob: dayjs('2025-01-16'),
  gender: 'Male',
  placeOfBirthTown: 'onto',
  placeOfBirthCountry: 'yowza forgo through',
  nationality: 'ew even',
  nationalId: 'since suspiciously per',
  maritalStatus: 'Married',
  residentialAddress1: 'elastic shovel',
  residentialAddress3: 'courteous soulful',
  homePhone: 'yet',
  emailAddress: 'customise however',
  nameOfPlaceOfEmployment: 'enhance pish yowza',
  employerEmail: 'for',
  dateOfEmployment: 'aside what',
  dateOfApplication: 'excepting',
  applicationFee: 'reapply quarrel',
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
  reasonForNonEmployment: 'swing for overstay',
  dateOfApplication: 'an',
  applicationFee: 'greedily',
  status: 'nauseate minus exhausted',
  reasonNotApproved: 'broken',
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
