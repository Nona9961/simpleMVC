package com.Nona9961.controllerContainer;

public class SimpleMVCControllerContainerFactory implements ControllerContainerFactory {
    @Override
    public ControllerContainer getControllerContainer() {
        return ControllerContainerImpl.getControllerContainer();
    }
}
