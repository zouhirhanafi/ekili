export interface IExamenClinique {
  id?: number;
  gcs?: number;
  pa?: string;
  diurese?: number;
  autre?: string;
}

export const defaultValue: Readonly<IExamenClinique> = {};
