import { IResource, NewResource } from './resource.model';

export const sampleWithRequiredData: IResource = {
  id: 17887,
};

export const sampleWithPartialData: IResource = {
  id: 42,
  resourceType: 'Food',
  quantity: 'besides noxious',
};

export const sampleWithFullData: IResource = {
  id: 12771,
  resourceType: 'Water',
  quantity: 'aching',
};

export const sampleWithNewData: NewResource = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
