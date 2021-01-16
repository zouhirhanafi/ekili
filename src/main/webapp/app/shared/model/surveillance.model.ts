import { ITraitementPerdialyse } from 'app/shared/model/traitement-perdialyse.model';
import { StatutSurveillance } from 'app/shared/model/enumerations/statut-surveillance.model';

export interface ISurveillance {
  id?: number;
  infirmier?: number;
  poste?: number;
  generateur?: number;
  statut?: StatutSurveillance;
  poid?: number;
  ufnet?: number;
  etatConscience?: number;
  eupneique?: number;
  restitutionPar?: number;
  autreComplication?: string;
  observation?: string;
  traitement?: ITraitementPerdialyse;
}

export const defaultValue: Readonly<ISurveillance> = {};
