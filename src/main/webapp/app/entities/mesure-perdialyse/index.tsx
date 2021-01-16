import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MesurePerdialyse from './mesure-perdialyse';
import MesurePerdialyseDetail from './mesure-perdialyse-detail';
import MesurePerdialyseUpdate from './mesure-perdialyse-update';
import MesurePerdialyseDeleteDialog from './mesure-perdialyse-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MesurePerdialyseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MesurePerdialyseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MesurePerdialyseDetail} />
      <ErrorBoundaryRoute path={match.url} component={MesurePerdialyse} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MesurePerdialyseDeleteDialog} />
  </>
);

export default Routes;
