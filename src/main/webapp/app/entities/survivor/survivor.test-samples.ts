import { ISurvivor, NewSurvivor } from './survivor.model';

export const sampleWithRequiredData: ISurvivor = {
  id: 8744,
};

export const sampleWithPartialData: ISurvivor = {
  id: 7840,
  name: 'gadzooks',
};

export const sampleWithFullData: ISurvivor = {
  id: 5085,
  name: 'sediment inasmuch',
};

export const sampleWithNewData: NewSurvivor = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
