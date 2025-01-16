import { ISurvivor, NewSurvivor } from './survivor.model';

export const sampleWithRequiredData: ISurvivor = {
  id: 8744,
};

export const sampleWithPartialData: ISurvivor = {
  id: 9462,
  survivorId: 'oh',
  name: 'bah against freely',
  age: 24013,
  gender: 'Unknown',
  latitude: 'although yahoo inventory',
  longitude: 'following with',
  status: 'Infected',
};

export const sampleWithFullData: ISurvivor = {
  id: 5085,
  survivorId: 'sediment inasmuch',
  name: 'blah',
  age: 27293,
  gender: 'Unknown',
  latitude: 'yet inasmuch translation',
  longitude: 'ouch',
  status: 'Normal',
};

export const sampleWithNewData: NewSurvivor = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
