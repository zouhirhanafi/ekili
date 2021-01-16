import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './surveillance.reducer';
import { ISurveillance } from 'app/shared/model/surveillance.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface ISurveillanceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Surveillance = (props: ISurveillanceProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get('sort');
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const { surveillanceList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="surveillance-heading">
        <Translate contentKey="ekiliApp.surveillance.home.title">Surveillances</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="ekiliApp.surveillance.home.createLabel">Create new Surveillance</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {surveillanceList && surveillanceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('infirmier')}>
                  <Translate contentKey="ekiliApp.surveillance.infirmier">Infirmier</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('poste')}>
                  <Translate contentKey="ekiliApp.surveillance.poste">Poste</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('generateur')}>
                  <Translate contentKey="ekiliApp.surveillance.generateur">Generateur</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('statut')}>
                  <Translate contentKey="ekiliApp.surveillance.statut">Statut</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('poid')}>
                  <Translate contentKey="ekiliApp.surveillance.poid">Poid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ufnet')}>
                  <Translate contentKey="ekiliApp.surveillance.ufnet">Ufnet</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('etatConscience')}>
                  <Translate contentKey="ekiliApp.surveillance.etatConscience">Etat Conscience</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('eupneique')}>
                  <Translate contentKey="ekiliApp.surveillance.eupneique">Eupneique</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('restitutionPar')}>
                  <Translate contentKey="ekiliApp.surveillance.restitutionPar">Restitution Par</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('autreComplication')}>
                  <Translate contentKey="ekiliApp.surveillance.autreComplication">Autre Complication</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('observation')}>
                  <Translate contentKey="ekiliApp.surveillance.observation">Observation</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="ekiliApp.surveillance.traitement">Traitement</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {surveillanceList.map((surveillance, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${surveillance.id}`} color="link" size="sm">
                      {surveillance.id}
                    </Button>
                  </td>
                  <td>{surveillance.infirmier}</td>
                  <td>{surveillance.poste}</td>
                  <td>{surveillance.generateur}</td>
                  <td>
                    <Translate contentKey={`ekiliApp.StatutSurveillance.${surveillance.statut}`} />
                  </td>
                  <td>{surveillance.poid}</td>
                  <td>{surveillance.ufnet}</td>
                  <td>{surveillance.etatConscience}</td>
                  <td>{surveillance.eupneique}</td>
                  <td>{surveillance.restitutionPar}</td>
                  <td>{surveillance.autreComplication}</td>
                  <td>{surveillance.observation}</td>
                  <td>
                    {surveillance.traitement ? (
                      <Link to={`traitement-perdialyse/${surveillance.traitement.id}`}>{surveillance.traitement.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${surveillance.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${surveillance.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${surveillance.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
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
              <Translate contentKey="ekiliApp.surveillance.home.notFound">No Surveillances found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={surveillanceList && surveillanceList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={props.totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

const mapStateToProps = ({ surveillance }: IRootState) => ({
  surveillanceList: surveillance.entities,
  loading: surveillance.loading,
  totalItems: surveillance.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Surveillance);
