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
  id: 32668,
  registrationNumber: 'against freely',
  surname: 'daintily likely ham',
  forenames: 'next',
  previousSurname: 'helplessly',
  dob: dayjs('2025-01-16'),
  gender: 'Female',
  placeOfBirthTown: 'expansion seemingly',
  placeOfBirthCountry: 'upon',
  nationality: 'mysterious slap',
  nationalId: 'athwart',
  maritalStatus: 'Married',
  residentialAddress1: 'rigidly ultimately queasily',
  residentialAddress2: 'bossy miserably phew',
  residentialAddress3: 'decide median shakily',
  workPhone: 'lashes scramble favorite',
  cellPhone: 'ring',
  employerAddress: 'yahoo ack disposer',
  employerEmail: 'conceal notwithstanding',
  reasonForNonEmployment: 'finally accept',
  dateOfApplication: 'famously er dual',
  status: 'ultimately',
  reasonNotApproved: 'detain woot',
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
  maritalStatus: 'Single',
  residentialAddress1: 'as inside spectate',
  residentialAddress2: 'oof',
  residentialAddress3: 'whenever that',
  homePhone: 'orderly after',
  workPhone: 'drab keenly',
  cellPhone: 'pleased haze',
  emailAddress: 'because citizen',
  nameOfPlaceOfEmployment: 'whenever on advertisement',
  employerAddress: 'cosset across',
  employerEmail: 'probe ah ick',
  dateOfEmployment: 'who woeful',
  reasonForNonEmployment: 'uh-huh',
  dateOfApplication: 'trash',
  applicationFee: 'swing for overstay',
  status: 'an',
  reasonNotApproved: 'greedily',
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
