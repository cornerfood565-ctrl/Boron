# BORON

BORON is an independent, client-side performance and rendering optimization mod for Minecraft: Java Edition.

## Target

- Minecraft: 1.21.11
- Mod loader: Fabric
- Java: 21
- Build: Gradle + Fabric Loom
- Mod ID: `boron`

## Current status

**Foundation / Stage 1**

- [x] Fabric project structure
- [x] Minecraft 1.21.11 dependency configuration
- [x] Client entry point
- [x] Persistent JSON configuration
- [ ] Verified local Gradle build
- [ ] Runtime test in a clean Fabric instance

The optimization switches currently establish configuration only. They do not claim to perform culling, chunk scheduling, or rendering changes yet. Each optimization will be implemented and benchmarked before being enabled.

## Planned modules

1. Rendering pipeline and GPU resource management
2. Chunk build scheduling and upload management
3. Entity and block-entity visibility optimizations
4. Particle processing optimizations
5. Memory and allocation management
6. Performance diagnostics and settings UI
7. Compatibility testing, including Android Java runtimes

## Build

Install JDK 21 and a compatible Gradle installation, then run:

```bash
gradle build
```

The remapped mod JAR will be generated in `build/libs/`.

## Development principles

- Implement original code; do not copy Sodium source.
- Keep risky rendering changes isolated behind configuration.
- Benchmark against unmodified Fabric on identical hardware and settings.
- Treat Android runtime compatibility as a separate test target.
