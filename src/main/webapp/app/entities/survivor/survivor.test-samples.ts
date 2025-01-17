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
  id: 20773,
  registrationNumber: 'regularly',
  title: 'Dr',
  surname: 'unto although yahoo',
  forenames: 'psst following',
  previousSurname: 'that hopeful',
  dob: dayjs('2025-01-16'),
  gender: 'Female',
  placeOfBirthTown: 'deceivingly',
  placeOfBirthCountry: 'seriously',
  nationality: 'cease',
  nationalId: 'an remand',
  maritalStatus: 'Single',
  residentialAddress1: 'suspiciously per infinite',
  residentialAddress2: 'pace gullible the',
  homePhone: 'ugh suffocate fluctuate',
  workPhone: 'ouch reconstitute',
  nameOfPlaceOfEmployment: 'um',
  employerAddress: 'an doting',
  dateOfEmployment: 'between for',
  reasonForNonEmployment: 'briefly netsuke',
  applicationFee: 'however bah upon',
  status: 'procrastinate questionably',
};

export const sampleWithFullData: ISurvivor = {
  id: 5085,
  registrationNumber: 'sediment inasmuch',
  title: 'Mr',
  surname: 'till',
  forenames: 'gadzooks inasmuch delightfully',
  previousSurname: 'male whose or',
  dob: dayjs('2025-01-16'),
  gender: 'Unknown',
  placeOfBirthTown: 'hydrolyse',
  placeOfBirthCountry: 'yippee lustrous wrongly',
  nationality: 'yearningly yogurt',
  nationalId: 'hm',
  maritalStatus: 'Married',
  residentialAddress1: 'almost nor pro',
  residentialAddress2: 'unto ouch phooey',
  residentialAddress3: 'ugh rudely',
  homePhone: 'infatuated blah whoever',
  workPhone: 'hmph',
  cellPhone: 'ha regarding pish',
  emailAddress: 'whose',
  nameOfPlaceOfEmployment: 'so masculinize',
  employerAddress: 'revere till yahoo',
  employerEmail: 'lotion tray vet',
  dateOfEmployment: 'affect whose',
  reasonForNonEmployment: 'boo swill amidst',
  dateOfApplication: 'quarrel',
  applicationFee: 'um pear implode',
  status: 'following',
  reasonNotApproved: 'boohoo',
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
