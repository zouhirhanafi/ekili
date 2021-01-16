import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './examen-clinique.reducer';
import { IExamenClinique } from 'app/shared/model/examen-clinique.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IExamenCliniqueUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ExamenCliniqueUpdate = (props: IExamenCliniqueUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { examenCliniqueEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/examen-clinique');
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
        ...examenCliniqueEntity,
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
          <h2 id="ekiliApp.examenClinique.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.examenClinique.home.createOrEditLabel">Create or edit a ExamenClinique</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : examenCliniqueEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="examen-clinique-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="examen-clinique-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="gcsLabel" for="examen-clinique-gcs">
                  <Translate contentKey="ekiliApp.examenClinique.gcs">Gcs</Translate>
                </Label>
                <AvField id="examen-clinique-gcs" type="string" className="form-control" name="gcs" />
              </AvGroup>
              <AvGroup>
                <Label id="paLabel" for="examen-clinique-pa">
                  <Translate contentKey="ekiliApp.examenClinique.pa">Pa</Translate>
                </Label>
                <AvField id="examen-clinique-pa" type="text" name="pa" />
              </AvGroup>
              <AvGroup>
                <Label id="diureseLabel" for="examen-clinique-diurese">
                  <Translate contentKey="ekiliApp.examenClinique.diurese">Diurese</Translate>
                </Label>
                <AvField id="examen-clinique-diurese" type="string" className="form-control" name="diurese" />
              </AvGroup>
              <AvGroup>
                <Label id="autreLabel" for="examen-clinique-autre">
                  <Translate contentKey="ekiliApp.examenClinique.autre">Autre</Translate>
                </Label>
                <AvField id="examen-clinique-autre" type="text" name="autre" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/examen-clinique" replace color="info">
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
  examenCliniqueEntity: storeState.examenClinique.entity,
  loading: storeState.examenClinique.loading,
  updating: storeState.examenClinique.updating,
  updateSuccess: storeState.examenClinique.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ExamenCliniqueUpdate);
