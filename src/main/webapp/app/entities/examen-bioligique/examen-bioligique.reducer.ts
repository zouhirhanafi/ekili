import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IExamenBioligique, defaultValue } from 'app/shared/model/examen-bioligique.model';

export const ACTION_TYPES = {
  FETCH_EXAMENBIOLIGIQUE_LIST: 'examenBioligique/FETCH_EXAMENBIOLIGIQUE_LIST',
  FETCH_EXAMENBIOLIGIQUE: 'examenBioligique/FETCH_EXAMENBIOLIGIQUE',
  CREATE_EXAMENBIOLIGIQUE: 'examenBioligique/CREATE_EXAMENBIOLIGIQUE',
  UPDATE_EXAMENBIOLIGIQUE: 'examenBioligique/UPDATE_EXAMENBIOLIGIQUE',
  DELETE_EXAMENBIOLIGIQUE: 'examenBioligique/DELETE_EXAMENBIOLIGIQUE',
  RESET: 'examenBioligique/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IExamenBioligique>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ExamenBioligiqueState = Readonly<typeof initialState>;

// Reducer

export default (state: ExamenBioligiqueState = initialState, action): ExamenBioligiqueState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EXAMENBIOLIGIQUE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EXAMENBIOLIGIQUE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_EXAMENBIOLIGIQUE):
    case REQUEST(ACTION_TYPES.UPDATE_EXAMENBIOLIGIQUE):
    case REQUEST(ACTION_TYPES.DELETE_EXAMENBIOLIGIQUE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_EXAMENBIOLIGIQUE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EXAMENBIOLIGIQUE):
    case FAILURE(ACTION_TYPES.CREATE_EXAMENBIOLIGIQUE):
    case FAILURE(ACTION_TYPES.UPDATE_EXAMENBIOLIGIQUE):
    case FAILURE(ACTION_TYPES.DELETE_EXAMENBIOLIGIQUE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXAMENBIOLIGIQUE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXAMENBIOLIGIQUE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_EXAMENBIOLIGIQUE):
    case SUCCESS(ACTION_TYPES.UPDATE_EXAMENBIOLIGIQUE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_EXAMENBIOLIGIQUE):
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

const apiUrl = 'api/examen-bioligiques';

// Actions

export const getEntities: ICrudGetAllAction<IExamenBioligique> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EXAMENBIOLIGIQUE_LIST,
  payload: axios.get<IExamenBioligique>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IExamenBioligique> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EXAMENBIOLIGIQUE,
    payload: axios.get<IExamenBioligique>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IExamenBioligique> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EXAMENBIOLIGIQUE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IExamenBioligique> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EXAMENBIOLIGIQUE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IExamenBioligique> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EXAMENBIOLIGIQUE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
