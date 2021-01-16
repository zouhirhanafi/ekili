export interface IDiagnostic {
  id?: number;
  hvb?: number;
  hvc?: number;
  vih?: number;
  poidSec?: number;
  autre?: string;
}

export const defaultValue: Readonly<IDiagnostic> = {};
