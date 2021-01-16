import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './indication-hd.reducer';
import { IIndicationHd } from 'app/shared/model/indication-hd.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IIndicationHdUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IndicationHdUpdate = (props: IIndicationHdUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { indicationHdEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/indication-hd');
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
        ...indicationHdEntity,
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
          <h2 id="ekiliApp.indicationHd.home.createOrEditLabel">
            <Translate contentKey="ekiliApp.indicationHd.home.createOrEditLabel">Create or edit a IndicationHd</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : indicationHdEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="indication-hd-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="indication-hd-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="serviceLabel" for="indication-hd-service">
                  <Translate contentKey="ekiliApp.indicationHd.service">Service</Translate>
                </Label>
                <AvField id="indication-hd-service" type="string" className="form-control" name="service" />
              </AvGroup>
              <AvGroup>
                <Label id="autreLabel" for="indication-hd-autre">
                  <Translate contentKey="ekiliApp.indicationHd.autre">Autre</Translate>
                </Label>
                <AvField id="indication-hd-autre" type="text" name="autre" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/indication-hd" replace color="info">
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
  indicationHdEntity: storeState.indicationHd.entity,
  loading: storeState.indicationHd.loading,
  updating: storeState.indicationHd.updating,
  updateSuccess: storeState.indicationHd.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IndicationHdUpdate);
