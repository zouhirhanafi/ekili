import { TypeTraitementPerdialyse } from 'app/shared/model/enumerations/type-traitement-perdialyse.model';

export interface ITraitementPerdialyse {
  id?: number;
  autre?: string;
  type?: TypeTraitementPerdialyse;
}

export const defaultValue: Readonly<ITraitementPerdialyse> = {};
