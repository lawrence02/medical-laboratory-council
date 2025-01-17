export interface ISurvivor {
  id: number;
  name?: string | null;
}

export type NewSurvivor = Omit<ISurvivor, 'id'> & { id: null };
