import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIndicationHd, defaultValue } from 'app/shared/model/indication-hd.model';

export const ACTION_TYPES = {
  FETCH_INDICATIONHD_LIST: 'indicationHd/FETCH_INDICATIONHD_LIST',
  FETCH_INDICATIONHD: 'indicationHd/FETCH_INDICATIONHD',
  CREATE_INDICATIONHD: 'indicationHd/CREATE_INDICATIONHD',
  UPDATE_INDICATIONHD: 'indicationHd/UPDATE_INDICATIONHD',
  DELETE_INDICATIONHD: 'indicationHd/DELETE_INDICATIONHD',
  RESET: 'indicationHd/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIndicationHd>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type IndicationHdState = Readonly<typeof initialState>;

// Reducer

export default (state: IndicationHdState = initialState, action): IndicationHdState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INDICATIONHD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INDICATIONHD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_INDICATIONHD):
    case REQUEST(ACTION_TYPES.UPDATE_INDICATIONHD):
    case REQUEST(ACTION_TYPES.DELETE_INDICATIONHD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_INDICATIONHD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INDICATIONHD):
    case FAILURE(ACTION_TYPES.CREATE_INDICATIONHD):
    case FAILURE(ACTION_TYPES.UPDATE_INDICATIONHD):
    case FAILURE(ACTION_TYPES.DELETE_INDICATIONHD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_INDICATIONHD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_INDICATIONHD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_INDICATIONHD):
    case SUCCESS(ACTION_TYPES.UPDATE_INDICATIONHD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_INDICATIONHD):
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

const apiUrl = 'api/indication-hds';

// Actions

export const getEntities: ICrudGetAllAction<IIndicationHd> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INDICATIONHD_LIST,
  payload: axios.get<IIndicationHd>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IIndicationHd> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INDICATIONHD,
    payload: axios.get<IIndicationHd>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IIndicationHd> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INDICATIONHD,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IIndicationHd> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INDICATIONHD,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIndicationHd> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INDICATIONHD,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
