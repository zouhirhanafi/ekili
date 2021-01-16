import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './traitement-perdialyse.reducer';
import { ITraitementPerdialyse } from 'app/shared/model/traitement-perdialyse.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITraitementPerdialyseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TraitementPerdialyseUpdate = (props: ITraitementPerdialyseUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { traitementPerdialyseEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/traitement-perdialyse');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...traitementPerdialyseEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="ekiliApp.traitementPerdialyse.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.traitementPerdialyse.home.createOrEditLabel">Create or edit a TraitementPerdialyse</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : traitementPerdialyseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="traitement-perdialyse-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="traitement-perdialyse-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="autreLabel" for="traitement-perdialyse-autre">
                  <Translate contentKey="ekiliApp.traitementPerdialyse.autre">Autre</Translate>
                </Label>
                <AvField id="traitement-perdialyse-autre" type="text" name="autre" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="traitement-perdialyse-type">
                  <Translate contentKey="ekiliApp.traitementPerdialyse.type">Type</Translate>
                </Label>
                <AvInput
                  id="traitement-perdialyse-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && traitementPerdialyseEntity.type) || 'P'}
                >
                  <option value="P">{translate('ekiliApp.TypeTraitementPerdialyse.P')}</option>
                  <option value="S">{translate('ekiliApp.TypeTraitementPerdialyse.S')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/traitement-perdialyse" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  traitementPerdialyseEntity: storeState.traitementPerdialyse.entity,
  loading: storeState.traitementPerdialyse.loading,
  updating: storeState.traitementPerdialyse.updating,
  updateSuccess: storeState.traitementPerdialyse.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TraitementPerdialyseUpdate);
