import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TraitementPerdialyse from './traitement-perdialyse';
import TraitementPerdialyseDetail from './traitement-perdialyse-detail';
import TraitementPerdialyseUpdate from './traitement-perdialyse-update';
import TraitementPerdialyseDeleteDialog from './traitement-perdialyse-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TraitementPerdialyseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TraitementPerdialyseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TraitementPerdialyseDetail} />
      <ErrorBoundaryRoute path={match.url} component={TraitementPerdialyse} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TraitementPerdialyseDeleteDialog} />
  </>
);

export default Routes;
