import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './dossier-patient.reducer';
import { IDossierPatient } from 'app/shared/model/dossier-patient.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

export interface IDossierPatientProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const DossierPatient = (props: IDossierPatientProps) => {
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

  const { dossierPatientList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="dossier-patient-heading">
        <Translate contentKey="ekiliApp.dossierPatient.home.title">Dossier Patients</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="ekiliApp.dossierPatient.home.createLabel">Create new Dossier Patient</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {dossierPatientList && dossierPatientList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ip')}>
                  <Translate contentKey="ekiliApp.dossierPatient.ip">Ip</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nom')}>
                  <Translate contentKey="ekiliApp.dossierPatient.nom">Nom</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('prenom')}>
                  <Translate contentKey="ekiliApp.dossierPatient.prenom">Prenom</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('genre')}>
                  <Translate contentKey="ekiliApp.dossierPatient.genre">Genre</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tel')}>
                  <Translate contentKey="ekiliApp.dossierPatient.tel">Tel</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('adresse')}>
                  <Translate contentKey="ekiliApp.dossierPatient.adresse">Adresse</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('amo')}>
                  <Translate contentKey="ekiliApp.dossierPatient.amo">Amo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('typeCentreOrigine')}>
                  <Translate contentKey="ekiliApp.dossierPatient.typeCentreOrigine">Type Centre Origine</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('villeCentreOrigine')}>
                  <Translate contentKey="ekiliApp.dossierPatient.villeCentreOrigine">Ville Centre Origine</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('observation')}>
                  <Translate contentKey="ekiliApp.dossierPatient.observation">Observation</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('naissance')}>
                  <Translate contentKey="ekiliApp.dossierPatient.naissance">Naissance</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="ekiliApp.dossierPatient.antecedent">Antecedent</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="ekiliApp.dossierPatient.diagnostic">Diagnostic</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="ekiliApp.dossierPatient.indicationHd">Indication Hd</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="ekiliApp.dossierPatient.examenClinique">Examen Clinique</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dossierPatientList.map((dossierPatient, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dossierPatient.id}`} color="link" size="sm">
                      {dossierPatient.id}
                    </Button>
                  </td>
                  <td>{dossierPatient.ip}</td>
                  <td>{dossierPatient.nom}</td>
                  <td>{dossierPatient.prenom}</td>
                  <td>{dossierPatient.genre}</td>
                  <td>{dossierPatient.tel}</td>
                  <td>{dossierPatient.adresse}</td>
                  <td>{dossierPatient.amo}</td>
                  <td>{dossierPatient.typeCentreOrigine}</td>
                  <td>{dossierPatient.villeCentreOrigine}</td>
                  <td>{dossierPatient.observation}</td>
                  <td>
                    {dossierPatient.naissance ? (
                      <TextFormat type="date" value={dossierPatient.naissance} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {dossierPatient.antecedent ? (
                      <Link to={`antecedent/${dossierPatient.antecedent.id}`}>{dossierPatient.antecedent.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {dossierPatient.diagnostic ? (
                      <Link to={`diagnostic/${dossierPatient.diagnostic.id}`}>{dossierPatient.diagnostic.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {dossierPatient.indicationHd ? (
                      <Link to={`indication-hd/${dossierPatient.indicationHd.id}`}>{dossierPatient.indicationHd.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {dossierPatient.examenClinique ? (
                      <Link to={`examen-clinique/${dossierPatient.examenClinique.id}`}>{dossierPatient.examenClinique.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dossierPatient.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${dossierPatient.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${dossierPatient.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="ekiliApp.dossierPatient.home.notFound">No Dossier Patients found</Translate>
            </div>
          )
        )}
      </div>
      {props.totalItems ? (
        <div className={dossierPatientList && dossierPatientList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ dossierPatient }: IRootState) => ({
  dossierPatientList: dossierPatient.entities,
  loading: dossierPatient.loading,
  totalItems: dossierPatient.totalItems,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DossierPatient);
