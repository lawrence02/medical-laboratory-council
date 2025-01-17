import dayjs from 'dayjs/esm';
import { Gender } from 'app/entities/enumerations/gender.model';

export interface ISurvivor {
  id: number;
  registrationNumber?: string | null;
  surname?: string | null;
  forenames?: string | null;
  previousSurname?: string | null;
  dob?: dayjs.Dayjs | null;
  gender?: keyof typeof Gender | null;
}

export type NewSurvivor = Omit<ISurvivor, 'id'> & { id: null };
