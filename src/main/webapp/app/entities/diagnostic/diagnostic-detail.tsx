import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './diagnostic.reducer';
import { IDiagnostic } from 'app/shared/model/diagnostic.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDiagnosticDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DiagnosticDetail = (props: IDiagnosticDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { diagnosticEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="ekiliApp.diagnostic.detail.title">Diagnostic</Translate> [<b>{diagnosticEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="hvb">
              <Translate contentKey="ekiliApp.diagnostic.hvb">Hvb</Translate>
            </span>
          </dt>
          <dd>{diagnosticEntity.hvb}</dd>
          <dt>
            <span id="hvc">
              <Translate contentKey="ekiliApp.diagnostic.hvc">Hvc</Translate>
            </span>
          </dt>
          <dd>{diagnosticEntity.hvc}</dd>
          <dt>
            <span id="vih">
              <Translate contentKey="ekiliApp.diagnostic.vih">Vih</Translate>
            </span>
          </dt>
          <dd>{diagnosticEntity.vih}</dd>
          <dt>
            <span id="poidSec">
              <Translate contentKey="ekiliApp.diagnostic.poidSec">Poid Sec</Translate>
            </span>
          </dt>
          <dd>{diagnosticEntity.poidSec}</dd>
          <dt>
            <span id="autre">
              <Translate contentKey="ekiliApp.diagnostic.autre">Autre</Translate>
            </span>
          </dt>
          <dd>{diagnosticEntity.autre}</dd>
        </dl>
        <Button tag={Link} to="/diagnostic" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/diagnostic/${diagnosticEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ diagnostic }: IRootState) => ({
  diagnosticEntity: diagnostic.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DiagnosticDetail);
