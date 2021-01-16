import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './examen-clinique.reducer';
import { IExamenClinique } from 'app/shared/model/examen-clinique.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExamenCliniqueDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ExamenCliniqueDetail = (props: IExamenCliniqueDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { examenCliniqueEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.examenClinique.detail.title">ExamenClinique</Translate> [<b>{examenCliniqueEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="gcs">
              <Translate contentKey="ekiliApp.examenClinique.gcs">Gcs</Translate>
            </span>
          </dt>
          <dd>{examenCliniqueEntity.gcs}</dd>
          <dt>
            <span id="pa">
              <Translate contentKey="ekiliApp.examenClinique.pa">Pa</Translate>
            </span>
          </dt>
          <dd>{examenCliniqueEntity.pa}</dd>
          <dt>
            <span id="diurese">
              <Translate contentKey="ekiliApp.examenClinique.diurese">Diurese</Translate>
            </span>
          </dt>
          <dd>{examenCliniqueEntity.diurese}</dd>
          <dt>
            <span id="autre">
              <Translate contentKey="ekiliApp.examenClinique.autre">Autre</Translate>
            </span>
          </dt>
          <dd>{examenCliniqueEntity.autre}</dd>
        </dl>
        <Button tag={Link} to="/examen-clinique" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/examen-clinique/${examenCliniqueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ examenClinique }: IRootState) => ({
  examenCliniqueEntity: examenClinique.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ExamenCliniqueDetail);
