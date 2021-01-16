import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './traitement-perdialyse.reducer';
import { ITraitementPerdialyse } from 'app/shared/model/traitement-perdialyse.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITraitementPerdialyseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const TraitementPerdialyse = (props: ITraitementPerdialyseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { traitementPerdialyseList, match, loading } = props;
  return (
    <div>
      <h2 id="traitement-perdialyse-heading">
        <Translate contentKey="ekiliApp.traitementPerdialyse.home.title">Traitement Perdialyses</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="ekiliApp.traitementPerdialyse.home.createLabel">Create new Traitement Perdialyse</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {traitementPerdialyseList && traitementPerdialyseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.traitementPerdialyse.autre">Autre</Translate>
                </th>
                <th>
                  <Translate contentKey="ekiliApp.traitementPerdialyse.type">Type</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {traitementPerdialyseList.map((traitementPerdialyse, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${traitementPerdialyse.id}`} color="link" size="sm">
                      {traitementPerdialyse.id}
                    </Button>
                  </td>
                  <td>{traitementPerdialyse.autre}</td>
                  <td>
                    <Translate contentKey={`ekiliApp.TypeTraitementPerdialyse.${traitementPerdialyse.type}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${traitementPerdialyse.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${traitementPerdialyse.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${traitementPerdialyse.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="ekiliApp.traitementPerdialyse.home.notFound">No Traitement Perdialyses found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ traitementPerdialyse }: IRootState) => ({
  traitementPerdialyseList: traitementPerdialyse.entities,
  loading: traitementPerdialyse.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TraitementPerdialyse);
