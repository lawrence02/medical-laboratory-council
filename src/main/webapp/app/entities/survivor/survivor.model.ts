import dayjs from 'dayjs/esm';
import { Title } from 'app/entities/enumerations/title.model';
import { Gender } from 'app/entities/enumerations/gender.model';
import { MaritalStatus } from 'app/entities/enumerations/marital-status.model';

export interface ISurvivor {
  id: number;
  registrationNumber?: string | null;
  title?: keyof typeof Title | null;
  surname?: string | null;
  forenames?: string | null;
  previousSurname?: string | null;
  dob?: dayjs.Dayjs | null;
  gender?: keyof typeof Gender | null;
  placeOfBirthTown?: string | null;
  placeOfBirthCountry?: string | null;
  nationality?: string | null;
  nationalId?: string | null;
  maritalStatus?: keyof typeof MaritalStatus | null;
  residentialAddress1?: string | null;
  residentialAddress2?: string | null;
  residentialAddress3?: string | null;
  homePhone?: string | null;
  workPhone?: string | null;
  cellPhone?: string | null;
  emailAddress?: string | null;
  nameOfPlaceOfEmployment?: string | null;
  employerAddress?: string | null;
  employerEmail?: string | null;
  dateOfEmployment?: string | null;
  reasonForNonEmployment?: string | null;
  dateOfApplication?: string | null;
  applicationFee?: string | null;
  status?: string | null;
  reasonNotApproved?: string | null;
}

export type NewSurvivor = Omit<ISurvivor, 'id'> & { id: null };
