import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ExamenBioligique from './examen-bioligique';
import ExamenBioligiqueDetail from './examen-bioligique-detail';
import ExamenBioligiqueUpdate from './examen-bioligique-update';
import ExamenBioligiqueDeleteDialog from './examen-bioligique-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ExamenBioligiqueUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ExamenBioligiqueUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExamenBioligiqueDetail} />
      <ErrorBoundaryRoute path={match.url} component={ExamenBioligique} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ExamenBioligiqueDeleteDialog} />
  </>
);

export default Routes;
