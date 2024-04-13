import * as React from 'react';
import {useTheme} from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
// @ts-ignore
import SwipeableViews from 'react-swipeable-views';
// @ts-ignore
import {autoPlay} from 'react-swipeable-views-utils';
import {useContext} from "react";
import {Context} from "../index";

const AutoPlaySwipeableViews = autoPlay(SwipeableViews);



function SwiperImages() {
    const museum = useContext(Context)

    const theme = useTheme();
    const [activeStep, setActiveStep] = React.useState(0);

    const handleStepChange = (step: number) => {
        setActiveStep(step);
    };

    return (
        <Box sx={{maxWidth: 400, flexGrow: 1}}>
            <Paper
                square
                elevation={0}
                sx={{
                    display: 'flex',
                    alignItems: 'center',
                    height: 50,
                    pl: 2,
                    bgcolor: 'background.default',
                }}
            >
            </Paper>
            <AutoPlaySwipeableViews
                axis={theme.direction === 'rtl' ? 'x-reverse' : 'x'}
                index={activeStep}
                onChangeIndex={handleStepChange}
                enableMouseEvents
            >
                {museum?.museumItems.map((step, index) => (
                    <div key={step.exhibitName}>
                        <p className="h-40">
                            <object type="image/jpeg" data={step.image[index]}></object>
                            <p>
                                Категория:
                                {step.exhibitGroup}
                            </p>
                            <p>
                                Описание:
                                {step.exhibitDescription}
                            </p>
                        </p>
                        {Math.abs(activeStep - index) <= 2 ? (
                            <Box
                                sx={{
                                    height: 255,
                                    display: 'block',
                                    maxWidth: 400,
                                    overflow: 'hidden',
                                    width: '100%',
                                }}
                            >

                            </Box>
                        ) : null}
                    </div>
                ))}
            </AutoPlaySwipeableViews>
        </Box>
    );
}

export default SwiperImages;