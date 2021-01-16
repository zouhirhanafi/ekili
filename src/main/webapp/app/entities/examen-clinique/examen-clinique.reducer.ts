import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IExamenClinique, defaultValue } from 'app/shared/model/examen-clinique.model';

export const ACTION_TYPES = {
  FETCH_EXAMENCLINIQUE_LIST: 'examenClinique/FETCH_EXAMENCLINIQUE_LIST',
  FETCH_EXAMENCLINIQUE: 'examenClinique/FETCH_EXAMENCLINIQUE',
  CREATE_EXAMENCLINIQUE: 'examenClinique/CREATE_EXAMENCLINIQUE',
  UPDATE_EXAMENCLINIQUE: 'examenClinique/UPDATE_EXAMENCLINIQUE',
  DELETE_EXAMENCLINIQUE: 'examenClinique/DELETE_EXAMENCLINIQUE',
  RESET: 'examenClinique/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IExamenClinique>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ExamenCliniqueState = Readonly<typeof initialState>;

// Reducer

export default (state: ExamenCliniqueState = initialState, action): ExamenCliniqueState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EXAMENCLINIQUE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EXAMENCLINIQUE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_EXAMENCLINIQUE):
    case REQUEST(ACTION_TYPES.UPDATE_EXAMENCLINIQUE):
    case REQUEST(ACTION_TYPES.DELETE_EXAMENCLINIQUE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_EXAMENCLINIQUE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EXAMENCLINIQUE):
    case FAILURE(ACTION_TYPES.CREATE_EXAMENCLINIQUE):
    case FAILURE(ACTION_TYPES.UPDATE_EXAMENCLINIQUE):
    case FAILURE(ACTION_TYPES.DELETE_EXAMENCLINIQUE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXAMENCLINIQUE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXAMENCLINIQUE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_EXAMENCLINIQUE):
    case SUCCESS(ACTION_TYPES.UPDATE_EXAMENCLINIQUE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_EXAMENCLINIQUE):
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

const apiUrl = 'api/examen-cliniques';

// Actions

export const getEntities: ICrudGetAllAction<IExamenClinique> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EXAMENCLINIQUE_LIST,
  payload: axios.get<IExamenClinique>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IExamenClinique> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EXAMENCLINIQUE,
    payload: axios.get<IExamenClinique>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IExamenClinique> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EXAMENCLINIQUE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IExamenClinique> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EXAMENCLINIQUE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IExamenClinique> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EXAMENCLINIQUE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
