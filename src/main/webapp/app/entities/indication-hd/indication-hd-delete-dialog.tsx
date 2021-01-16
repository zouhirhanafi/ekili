import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IIndicationHd } from 'app/shared/model/indication-hd.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './indication-hd.reducer';

export interface IIndicationHdDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IndicationHdDeleteDialog = (props: IIndicationHdDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/indication-hd');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.indicationHdEntity.id);
  };

  const { indicationHdEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="ekiliApp.indicationHd.delete.question">
        <Translate contentKey="ekiliApp.indicationHd.delete.question" interpolate={{ id: indicationHdEntity.id }}>
          Are you sure you want to delete this IndicationHd?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-indicationHd" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ indicationHd }: IRootState) => ({
  indicationHdEntity: indicationHd.entity,
  updateSuccess: indicationHd.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IndicationHdDeleteDialog);
