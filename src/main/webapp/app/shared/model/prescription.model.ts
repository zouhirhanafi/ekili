import { Moment } from 'moment';
import { ITraitementPerdialyse } from 'app/shared/model/traitement-perdialyse.model';
import { ISurveillance } from 'app/shared/model/surveillance.model';
import { IDossierPatient } from 'app/shared/model/dossier-patient.model';
import { StatutPrescription } from 'app/shared/model/enumerations/statut-prescription.model';

export interface IPrescription {
  id?: number;
  duree?: number;
  capillaire?: number;
  restitutionP?: number;
  niveauUrgence?: number;
  ufTotale?: number;
  rincage?: number;
  transfusion?: number;
  datePlanification?: string;
  circuit?: number;
  abordVasculaire?: number;
  profil?: number;
  conductiviteP?: number;
  debitPompe?: number;
  temperatureDialysat?: number;
  atc?: boolean;
  hnfh0?: number;
  hnfh2?: number;
  hbpm?: number;
  statut?: StatutPrescription;
  motifAnnulation?: number;
  motifReport?: number;
  observationP?: string;
  traitement?: ITraitementPerdialyse;
  surveillance?: ISurveillance;
  patient?: IDossierPatient;
}

export const defaultValue: Readonly<IPrescription> = {
  atc: false,
};
