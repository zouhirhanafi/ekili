import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import IndicationHd from './indication-hd';
import IndicationHdDetail from './indication-hd-detail';
import IndicationHdUpdate from './indication-hd-update';
import IndicationHdDeleteDialog from './indication-hd-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IndicationHdUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IndicationHdUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IndicationHdDetail} />
      <ErrorBoundaryRoute path={match.url} component={IndicationHd} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={IndicationHdDeleteDialog} />
  </>
);

export default Routes;
