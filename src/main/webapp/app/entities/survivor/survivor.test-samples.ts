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
  id: 7840,
  registrationNumber: 'gadzooks',
  surname: 'across accompanist',
  forenames: 'shark yet since',
  previousSurname: 'yahoo',
  dob: dayjs('2025-01-16'),
  gender: 'Male',
};

export const sampleWithFullData: ISurvivor = {
  id: 5085,
  registrationNumber: 'sediment inasmuch',
  surname: 'blah',
  forenames: 'consequently likely hope',
  previousSurname: 'truthfully',
  dob: dayjs('2025-01-16'),
  gender: 'Unknown',
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
