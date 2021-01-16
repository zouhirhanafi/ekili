import { Moment } from 'moment';
import { IAntecedent } from 'app/shared/model/antecedent.model';
import { IDiagnostic } from 'app/shared/model/diagnostic.model';
import { IIndicationHd } from 'app/shared/model/indication-hd.model';
import { IExamenClinique } from 'app/shared/model/examen-clinique.model';

export interface IDossierPatient {
  id?: number;
  ip?: number;
  nom?: string;
  prenom?: string;
  genre?: number;
  tel?: string;
  adresse?: string;
  amo?: number;
  typeCentreOrigine?: number;
  villeCentreOrigine?: number;
  observation?: string;
  naissance?: string;
  antecedent?: IAntecedent;
  diagnostic?: IDiagnostic;
  indicationHd?: IIndicationHd;
  examenClinique?: IExamenClinique;
}

export const defaultValue: Readonly<IDossierPatient> = {};
