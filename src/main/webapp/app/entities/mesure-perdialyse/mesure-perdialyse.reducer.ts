import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMesurePerdialyse, defaultValue } from 'app/shared/model/mesure-perdialyse.model';

export const ACTION_TYPES = {
  FETCH_MESUREPERDIALYSE_LIST: 'mesurePerdialyse/FETCH_MESUREPERDIALYSE_LIST',
  FETCH_MESUREPERDIALYSE: 'mesurePerdialyse/FETCH_MESUREPERDIALYSE',
  CREATE_MESUREPERDIALYSE: 'mesurePerdialyse/CREATE_MESUREPERDIALYSE',
  UPDATE_MESUREPERDIALYSE: 'mesurePerdialyse/UPDATE_MESUREPERDIALYSE',
  DELETE_MESUREPERDIALYSE: 'mesurePerdialyse/DELETE_MESUREPERDIALYSE',
  RESET: 'mesurePerdialyse/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMesurePerdialyse>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MesurePerdialyseState = Readonly<typeof initialState>;

// Reducer

export default (state: MesurePerdialyseState = initialState, action): MesurePerdialyseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MESUREPERDIALYSE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MESUREPERDIALYSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MESUREPERDIALYSE):
    case REQUEST(ACTION_TYPES.UPDATE_MESUREPERDIALYSE):
    case REQUEST(ACTION_TYPES.DELETE_MESUREPERDIALYSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MESUREPERDIALYSE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MESUREPERDIALYSE):
    case FAILURE(ACTION_TYPES.CREATE_MESUREPERDIALYSE):
    case FAILURE(ACTION_TYPES.UPDATE_MESUREPERDIALYSE):
    case FAILURE(ACTION_TYPES.DELETE_MESUREPERDIALYSE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MESUREPERDIALYSE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MESUREPERDIALYSE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MESUREPERDIALYSE):
    case SUCCESS(ACTION_TYPES.UPDATE_MESUREPERDIALYSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MESUREPERDIALYSE):
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

const apiUrl = 'api/mesure-perdialyses';

// Actions

export const getEntities: ICrudGetAllAction<IMesurePerdialyse> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MESUREPERDIALYSE_LIST,
  payload: axios.get<IMesurePerdialyse>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMesurePerdialyse> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MESUREPERDIALYSE,
    payload: axios.get<IMesurePerdialyse>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMesurePerdialyse> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MESUREPERDIALYSE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMesurePerdialyse> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MESUREPERDIALYSE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMesurePerdialyse> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MESUREPERDIALYSE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
