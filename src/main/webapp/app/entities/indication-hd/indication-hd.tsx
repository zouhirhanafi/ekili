import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './indication-hd.reducer';
import { IIndicationHd } from 'app/shared/model/indication-hd.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIndicationHdProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const IndicationHd = (props: IIndicationHdProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { indicationHdList, match, loading } = props;
  return (
    <div>
      <h2 id="indication-hd-heading">
        <Translate contentKey="ekiliApp.indicationHd.home.title">Indication Hds</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="ekiliApp.indicationHd.home.createLabel">Create new Indication Hd</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {indicationHdList && indicationHdList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.indicationHd.service">Service</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.indicationHd.autre">Autre</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {indicationHdList.map((indicationHd, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${indicationHd.id}`} color="link" size="sm">
                      {indicationHd.id}
                    </Button>
                  </td>
                  <td>{indicationHd.service}</td>
                  <td>{indicationHd.autre}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${indicationHd.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${indicationHd.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${indicationHd.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="ekiliApp.indicationHd.home.notFound">No Indication Hds found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ indicationHd }: IRootState) => ({
  indicationHdList: indicationHd.entities,
  loading: indicationHd.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IndicationHd);
