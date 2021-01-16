import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDossierPatient, defaultValue } from 'app/shared/model/dossier-patient.model';

export const ACTION_TYPES = {
  FETCH_DOSSIERPATIENT_LIST: 'dossierPatient/FETCH_DOSSIERPATIENT_LIST',
  FETCH_DOSSIERPATIENT: 'dossierPatient/FETCH_DOSSIERPATIENT',
  CREATE_DOSSIERPATIENT: 'dossierPatient/CREATE_DOSSIERPATIENT',
  UPDATE_DOSSIERPATIENT: 'dossierPatient/UPDATE_DOSSIERPATIENT',
  DELETE_DOSSIERPATIENT: 'dossierPatient/DELETE_DOSSIERPATIENT',
  RESET: 'dossierPatient/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDossierPatient>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type DossierPatientState = Readonly<typeof initialState>;

// Reducer

export default (state: DossierPatientState = initialState, action): DossierPatientState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DOSSIERPATIENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DOSSIERPATIENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DOSSIERPATIENT):
    case REQUEST(ACTION_TYPES.UPDATE_DOSSIERPATIENT):
    case REQUEST(ACTION_TYPES.DELETE_DOSSIERPATIENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DOSSIERPATIENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DOSSIERPATIENT):
    case FAILURE(ACTION_TYPES.CREATE_DOSSIERPATIENT):
    case FAILURE(ACTION_TYPES.UPDATE_DOSSIERPATIENT):
    case FAILURE(ACTION_TYPES.DELETE_DOSSIERPATIENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DOSSIERPATIENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_DOSSIERPATIENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DOSSIERPATIENT):
    case SUCCESS(ACTION_TYPES.UPDATE_DOSSIERPATIENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DOSSIERPATIENT):
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

const apiUrl = 'api/dossier-patients';

// Actions

export const getEntities: ICrudGetAllAction<IDossierPatient> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DOSSIERPATIENT_LIST,
    payload: axios.get<IDossierPatient>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IDossierPatient> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DOSSIERPATIENT,
    payload: axios.get<IDossierPatient>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDossierPatient> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DOSSIERPATIENT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDossierPatient> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DOSSIERPATIENT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDossierPatient> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DOSSIERPATIENT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
