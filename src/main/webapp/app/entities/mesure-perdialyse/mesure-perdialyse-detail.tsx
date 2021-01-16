import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './mesure-perdialyse.reducer';
import { IMesurePerdialyse } from 'app/shared/model/mesure-perdialyse.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMesurePerdialyseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MesurePerdialyseDetail = (props: IMesurePerdialyseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { mesurePerdialyseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.mesurePerdialyse.detail.title">MesurePerdialyse</Translate> [<b>{mesurePerdialyseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="heure">
              <Translate contentKey="ekiliApp.mesurePerdialyse.heure">Heure</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.heure}</dd>
          <dt>
            <span id="poid">
              <Translate contentKey="ekiliApp.mesurePerdialyse.poid">Poid</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.poid}</dd>
          <dt>
            <span id="ta">
              <Translate contentKey="ekiliApp.mesurePerdialyse.ta">Ta</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.ta}</dd>
          <dt>
            <span id="tp">
              <Translate contentKey="ekiliApp.mesurePerdialyse.tp">Tp</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.tp}</dd>
          <dt>
            <span id="dextro">
              <Translate contentKey="ekiliApp.mesurePerdialyse.dextro">Dextro</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.dextro}</dd>
          <dt>
            <span id="pa">
              <Translate contentKey="ekiliApp.mesurePerdialyse.pa">Pa</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.pa}</dd>
          <dt>
            <span id="pv">
              <Translate contentKey="ekiliApp.mesurePerdialyse.pv">Pv</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.pv}</dd>
          <dt>
            <span id="ptm">
              <Translate contentKey="ekiliApp.mesurePerdialyse.ptm">Ptm</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.ptm}</dd>
          <dt>
            <span id="ufh">
              <Translate contentKey="ekiliApp.mesurePerdialyse.ufh">Ufh</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.ufh}</dd>
          <dt>
            <span id="conductivite">
              <Translate contentKey="ekiliApp.mesurePerdialyse.conductivite">Conductivite</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.conductivite}</dd>
          <dt>
            <span id="td">
              <Translate contentKey="ekiliApp.mesurePerdialyse.td">Td</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.td}</dd>
          <dt>
            <span id="dps">
              <Translate contentKey="ekiliApp.mesurePerdialyse.dps">Dps</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.dps}</dd>
          <dt>
            <span id="heparine">
              <Translate contentKey="ekiliApp.mesurePerdialyse.heparine">Heparine</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.heparine}</dd>
          <dt>
            <span id="rincage">
              <Translate contentKey="ekiliApp.mesurePerdialyse.rincage">Rincage</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.rincage}</dd>
          <dt>
            <span id="transfusion">
              <Translate contentKey="ekiliApp.mesurePerdialyse.transfusion">Transfusion</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.transfusion}</dd>
          <dt>
            <span id="numPoche">
              <Translate contentKey="ekiliApp.mesurePerdialyse.numPoche">Num Poche</Translate>
            </span>
          </dt>
          <dd>{mesurePerdialyseEntity.numPoche}</dd>
          <dt>
            <Translate contentKey="ekiliApp.mesurePerdialyse.surveillance">Surveillance</Translate>
          </dt>
          <dd>{mesurePerdialyseEntity.surveillance ? mesurePerdialyseEntity.surveillance.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/mesure-perdialyse" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/mesure-perdialyse/${mesurePerdialyseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ mesurePerdialyse }: IRootState) => ({
  mesurePerdialyseEntity: mesurePerdialyse.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MesurePerdialyseDetail);
