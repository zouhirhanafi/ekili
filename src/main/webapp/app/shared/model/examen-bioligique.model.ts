import { Moment } from 'moment';
import { IDossierPatient } from 'app/shared/model/dossier-patient.model';

export interface IExamenBioligique {
  id?: number;
  date?: string;
  uree?: number;
  creat?: number;
  k?: number;
  na?: number;
  ca?: number;
  crp?: number;
  hb?: number;
  gb?: number;
  plt?: number;
  acHbs?: string;
  agHbs?: number;
  hbc?: string;
  acHvc?: string;
  vih?: string;
  autre?: string;
  patient?: IDossierPatient;
}

export const defaultValue: Readonly<IExamenBioligique> = {};
