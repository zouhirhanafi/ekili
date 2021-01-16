import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './indication-hd.reducer';
import { IIndicationHd } from 'app/shared/model/indication-hd.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIndicationHdDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IndicationHdDetail = (props: IIndicationHdDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { indicationHdEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.indicationHd.detail.title">IndicationHd</Translate> [<b>{indicationHdEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="service">
              <Translate contentKey="ekiliApp.indicationHd.service">Service</Translate>
            </span>
          </dt>
          <dd>{indicationHdEntity.service}</dd>
          <dt>
            <span id="autre">
              <Translate contentKey="ekiliApp.indicationHd.autre">Autre</Translate>
            </span>
          </dt>
          <dd>{indicationHdEntity.autre}</dd>
        </dl>
        <Button tag={Link} to="/indication-hd" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/indication-hd/${indicationHdEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ indicationHd }: IRootState) => ({
  indicationHdEntity: indicationHd.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IndicationHdDetail);
