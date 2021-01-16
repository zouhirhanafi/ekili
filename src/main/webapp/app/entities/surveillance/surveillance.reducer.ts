import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISurveillance, defaultValue } from 'app/shared/model/surveillance.model';

export const ACTION_TYPES = {
  FETCH_SURVEILLANCE_LIST: 'surveillance/FETCH_SURVEILLANCE_LIST',
  FETCH_SURVEILLANCE: 'surveillance/FETCH_SURVEILLANCE',
  CREATE_SURVEILLANCE: 'surveillance/CREATE_SURVEILLANCE',
  UPDATE_SURVEILLANCE: 'surveillance/UPDATE_SURVEILLANCE',
  DELETE_SURVEILLANCE: 'surveillance/DELETE_SURVEILLANCE',
  RESET: 'surveillance/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISurveillance>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type SurveillanceState = Readonly<typeof initialState>;

// Reducer

export default (state: SurveillanceState = initialState, action): SurveillanceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SURVEILLANCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SURVEILLANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SURVEILLANCE):
    case REQUEST(ACTION_TYPES.UPDATE_SURVEILLANCE):
    case REQUEST(ACTION_TYPES.DELETE_SURVEILLANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SURVEILLANCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SURVEILLANCE):
    case FAILURE(ACTION_TYPES.CREATE_SURVEILLANCE):
    case FAILURE(ACTION_TYPES.UPDATE_SURVEILLANCE):
    case FAILURE(ACTION_TYPES.DELETE_SURVEILLANCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SURVEILLANCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_SURVEILLANCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SURVEILLANCE):
    case SUCCESS(ACTION_TYPES.UPDATE_SURVEILLANCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SURVEILLANCE):
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

const apiUrl = 'api/surveillances';

// Actions

export const getEntities: ICrudGetAllAction<ISurveillance> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_SURVEILLANCE_LIST,
    payload: axios.get<ISurveillance>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ISurveillance> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SURVEILLANCE,
    payload: axios.get<ISurveillance>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISurveillance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SURVEILLANCE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISurveillance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SURVEILLANCE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISurveillance> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SURVEILLANCE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
