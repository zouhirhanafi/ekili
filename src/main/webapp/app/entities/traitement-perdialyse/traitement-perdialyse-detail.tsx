import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './traitement-perdialyse.reducer';
import { ITraitementPerdialyse } from 'app/shared/model/traitement-perdialyse.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITraitementPerdialyseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TraitementPerdialyseDetail = (props: ITraitementPerdialyseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { traitementPerdialyseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.traitementPerdialyse.detail.title">TraitementPerdialyse</Translate> [
          <b>{traitementPerdialyseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="autre">
              <Translate contentKey="ekiliApp.traitementPerdialyse.autre">Autre</Translate>
            </span>
          </dt>
          <dd>{traitementPerdialyseEntity.autre}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="ekiliApp.traitementPerdialyse.type">Type</Translate>
            </span>
          </dt>
          <dd>{traitementPerdialyseEntity.type}</dd>
        </dl>
        <Button tag={Link} to="/traitement-perdialyse" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/traitement-perdialyse/${traitementPerdialyseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ traitementPerdialyse }: IRootState) => ({
  traitementPerdialyseEntity: traitementPerdialyse.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TraitementPerdialyseDetail);
