import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './diagnostic.reducer';
import { IDiagnostic } from 'app/shared/model/diagnostic.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDiagnosticUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DiagnosticUpdate = (props: IDiagnosticUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { diagnosticEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/diagnostic');
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
        ...diagnosticEntity,
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
          <h2 id="ekiliApp.diagnostic.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.diagnostic.home.createOrEditLabel">Create or edit a Diagnostic</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : diagnosticEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="diagnostic-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="diagnostic-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="hvbLabel" for="diagnostic-hvb">
                  <Translate contentKey="ekiliApp.diagnostic.hvb">Hvb</Translate>
                </Label>
                <AvField id="diagnostic-hvb" type="string" className="form-control" name="hvb" />
              </AvGroup>
              <AvGroup>
                <Label id="hvcLabel" for="diagnostic-hvc">
                  <Translate contentKey="ekiliApp.diagnostic.hvc">Hvc</Translate>
                </Label>
                <AvField id="diagnostic-hvc" type="string" className="form-control" name="hvc" />
              </AvGroup>
              <AvGroup>
                <Label id="vihLabel" for="diagnostic-vih">
                  <Translate contentKey="ekiliApp.diagnostic.vih">Vih</Translate>
                </Label>
                <AvField id="diagnostic-vih" type="string" className="form-control" name="vih" />
              </AvGroup>
              <AvGroup>
                <Label id="poidSecLabel" for="diagnostic-poidSec">
                  <Translate contentKey="ekiliApp.diagnostic.poidSec">Poid Sec</Translate>
                </Label>
                <AvField id="diagnostic-poidSec" type="string" className="form-control" name="poidSec" />
              </AvGroup>
              <AvGroup>
                <Label id="autreLabel" for="diagnostic-autre">
                  <Translate contentKey="ekiliApp.diagnostic.autre">Autre</Translate>
                </Label>
                <AvField id="diagnostic-autre" type="text" name="autre" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/diagnostic" replace color="info">
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
  diagnosticEntity: storeState.diagnostic.entity,
  loading: storeState.diagnostic.loading,
  updating: storeState.diagnostic.updating,
  updateSuccess: storeState.diagnostic.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DiagnosticUpdate);
