import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITraitementPerdialyse, defaultValue } from 'app/shared/model/traitement-perdialyse.model';

export const ACTION_TYPES = {
  FETCH_TRAITEMENTPERDIALYSE_LIST: 'traitementPerdialyse/FETCH_TRAITEMENTPERDIALYSE_LIST',
  FETCH_TRAITEMENTPERDIALYSE: 'traitementPerdialyse/FETCH_TRAITEMENTPERDIALYSE',
  CREATE_TRAITEMENTPERDIALYSE: 'traitementPerdialyse/CREATE_TRAITEMENTPERDIALYSE',
  UPDATE_TRAITEMENTPERDIALYSE: 'traitementPerdialyse/UPDATE_TRAITEMENTPERDIALYSE',
  DELETE_TRAITEMENTPERDIALYSE: 'traitementPerdialyse/DELETE_TRAITEMENTPERDIALYSE',
  RESET: 'traitementPerdialyse/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITraitementPerdialyse>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TraitementPerdialyseState = Readonly<typeof initialState>;

// Reducer

export default (state: TraitementPerdialyseState = initialState, action): TraitementPerdialyseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRAITEMENTPERDIALYSE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRAITEMENTPERDIALYSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TRAITEMENTPERDIALYSE):
    case REQUEST(ACTION_TYPES.UPDATE_TRAITEMENTPERDIALYSE):
    case REQUEST(ACTION_TYPES.DELETE_TRAITEMENTPERDIALYSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TRAITEMENTPERDIALYSE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRAITEMENTPERDIALYSE):
    case FAILURE(ACTION_TYPES.CREATE_TRAITEMENTPERDIALYSE):
    case FAILURE(ACTION_TYPES.UPDATE_TRAITEMENTPERDIALYSE):
    case FAILURE(ACTION_TYPES.DELETE_TRAITEMENTPERDIALYSE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRAITEMENTPERDIALYSE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRAITEMENTPERDIALYSE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRAITEMENTPERDIALYSE):
    case SUCCESS(ACTION_TYPES.UPDATE_TRAITEMENTPERDIALYSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRAITEMENTPERDIALYSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/traitement-perdialyses';

// Actions

export const getEntities: ICrudGetAllAction<ITraitementPerdialyse> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRAITEMENTPERDIALYSE_LIST,
  payload: axios.get<ITraitementPerdialyse>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITraitementPerdialyse> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRAITEMENTPERDIALYSE,
    payload: axios.get<ITraitementPerdialyse>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITraitementPerdialyse> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRAITEMENTPERDIALYSE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITraitementPerdialyse> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRAITEMENTPERDIALYSE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITraitementPerdialyse> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRAITEMENTPERDIALYSE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
