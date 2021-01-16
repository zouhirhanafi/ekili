import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAntecedent, defaultValue } from 'app/shared/model/antecedent.model';

export const ACTION_TYPES = {
  FETCH_ANTECEDENT_LIST: 'antecedent/FETCH_ANTECEDENT_LIST',
  FETCH_ANTECEDENT: 'antecedent/FETCH_ANTECEDENT',
  CREATE_ANTECEDENT: 'antecedent/CREATE_ANTECEDENT',
  UPDATE_ANTECEDENT: 'antecedent/UPDATE_ANTECEDENT',
  DELETE_ANTECEDENT: 'antecedent/DELETE_ANTECEDENT',
  RESET: 'antecedent/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAntecedent>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AntecedentState = Readonly<typeof initialState>;

// Reducer

export default (state: AntecedentState = initialState, action): AntecedentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ANTECEDENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ANTECEDENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ANTECEDENT):
    case REQUEST(ACTION_TYPES.UPDATE_ANTECEDENT):
    case REQUEST(ACTION_TYPES.DELETE_ANTECEDENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ANTECEDENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ANTECEDENT):
    case FAILURE(ACTION_TYPES.CREATE_ANTECEDENT):
    case FAILURE(ACTION_TYPES.UPDATE_ANTECEDENT):
    case FAILURE(ACTION_TYPES.DELETE_ANTECEDENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ANTECEDENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ANTECEDENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ANTECEDENT):
    case SUCCESS(ACTION_TYPES.UPDATE_ANTECEDENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ANTECEDENT):
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

const apiUrl = 'api/antecedents';

// Actions

export const getEntities: ICrudGetAllAction<IAntecedent> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ANTECEDENT_LIST,
  payload: axios.get<IAntecedent>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAntecedent> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ANTECEDENT,
    payload: axios.get<IAntecedent>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAntecedent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ANTECEDENT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAntecedent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ANTECEDENT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAntecedent> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ANTECEDENT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
