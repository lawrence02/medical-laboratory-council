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
  id: 11921,
  registrationNumber: 'scotch innocently unless',
  surname: 'capitalize across',
  forenames: 'dependency pish',
  previousSurname: 'forager',
  dob: dayjs('2025-01-16'),
  gender: 'Unknown',
  placeOfBirthTown: 'famously meanwhile',
  placeOfBirthCountry: 'solvency now',
  nationality: 'now',
  nationalId: 'vibraphone circumnavigate outflank',
  residentialAddress1: 'markup netsuke courteous',
  residentialAddress2: 'able yet inside',
  residentialAddress3: 'however',
  homePhone: 'enhance pish yowza',
  cellPhone: 'for',
  emailAddress: 'aside what',
  employerEmail: 'excepting',
  dateOfEmployment: 'reapply quarrel',
  dateOfApplication: 'by',
  applicationFee: 'embossing',
  reasonNotApproved: 'er',
};

export const sampleWithFullData: ISurvivor = {
  id: 5085,
  registrationNumber: 'sediment inasmuch',
  surname: 'blah',
  forenames: 'consequently likely hope',
  previousSurname: 'truthfully',
  dob: dayjs('2025-01-16'),
  gender: 'Unknown',
  placeOfBirthTown: 'swine',
  placeOfBirthCountry: 'deficient',
  nationality: 'snoop yippee',
  nationalId: 'from',
  residentialAddress1: 'hierarchy representation',
  residentialAddress2: 'spectate oh',
  residentialAddress3: 'almost nor pro',
  homePhone: 'unto ouch phooey',
  workPhone: 'ugh rudely',
  cellPhone: 'infatuated blah whoever',
  emailAddress: 'hmph',
  nameOfPlaceOfEmployment: 'ha regarding pish',
  employerAddress: 'whose',
  employerEmail: 'so masculinize',
  dateOfEmployment: 'revere till yahoo',
  reasonForNonEmployment: 'lotion tray vet',
  dateOfApplication: 'affect whose',
  applicationFee: 'boo swill amidst',
  status: 'quarrel',
  reasonNotApproved: 'um pear implode',
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
