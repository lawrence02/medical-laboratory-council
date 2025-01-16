import { ISurvivor } from 'app/entities/survivor/survivor.model';
import { ResourceType } from 'app/entities/enumerations/resource-type.model';

export interface IResource {
  id: number;
  resourceType?: keyof typeof ResourceType | null;
  quantity?: string | null;
  survivor?: ISurvivor | null;
}

export type NewResource = Omit<IResource, 'id'> & { id: null };
