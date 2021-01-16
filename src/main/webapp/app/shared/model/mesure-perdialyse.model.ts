import { ISurveillance } from 'app/shared/model/surveillance.model';

export interface IMesurePerdialyse {
  id?: number;
  heure?: string;
  poid?: number;
  ta?: string;
  tp?: number;
  dextro?: number;
  pa?: string;
  pv?: number;
  ptm?: number;
  ufh?: number;
  conductivite?: number;
  td?: number;
  dps?: number;
  heparine?: number;
  rincage?: number;
  transfusion?: number;
  numPoche?: number;
  surveillance?: ISurveillance;
}

export const defaultValue: Readonly<IMesurePerdialyse> = {};
